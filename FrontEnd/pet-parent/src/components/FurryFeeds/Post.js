import React, { useEffect, useRef, useState } from "react";
import * as S from './styles';
import { Avatar, Box, IconButton, Typography } from "@mui/material";
import { Bookmark, BookmarkBorder, ChatBubbleOutline, EmojiEmotions, Favorite, FavoriteBorder, Share } from "@mui/icons-material";
import { useDispatch, useSelector } from "react-redux";
import { fetchLikedPostsByUser, fetchSavedPostsByUser, likePost, savePost, unlikePost, unsavePost } from "../../redux/actions/postActions";
import { addComment, fetchComments } from "../../redux/actions/fetchCommentAction";
import CommentsModal from "./CommentsModal";
import Picker from '@emoji-mart/react';
import data from '@emoji-mart/data';

const Post = React.forwardRef(({ post }, ref) => {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.auth.user);
    const { likedPosts, savedPosts } = useSelector((state) => state.post);
    
    const [isLikedByUser, setIsLikedByUser] = useState(false);
    const [isSavedByUser, setIsSavedByUser] = useState(false);
    const [likeCount, setLikeCount] = useState(post.likeCount);
    const [commentCount, setCommentCount] = useState(post.commentCount);
    const [savesCount, setSavesCount] = useState(post.savesCount);
    const [isCommentModalOpen, setIsCommentModalOpen] = useState(false);
    const [showCommentInput, setCommentShowInput] = useState(false);
    const [newComment, setNewComment] = useState('');
    const [showEmojiPicker, setShowEmojiPicker] = useState(false);

    const emojiPickerRef = useRef(null);

    useEffect(() => {
        dispatch(fetchLikedPostsByUser(user.userId));
        dispatch(fetchSavedPostsByUser(user.userId));
    }, [dispatch, user.userId]);

    useEffect(() => {
        setIsLikedByUser(likedPosts.some((likedPost) => likedPost.postId === post.postId));
    }, [likedPosts, post.postId]);

    useEffect(() => {
        setIsSavedByUser(savedPosts.some((savedPost) => savedPost.postId === post.postId));
    }, [savedPosts, post.postId]);

    const { createAt, imageUrl, content } = post;


    const handleLike = () => {
        if (isLikedByUser) {
            setLikeCount(likeCount - 1);
            setIsLikedByUser(false);
            dispatch(unlikePost(post.postId, user.userId));
        }
        else {
            setLikeCount(likeCount + 1);
            setIsLikedByUser(true);
            dispatch(likePost(post.postId, user.userId));
        }
    };

    const handleSave = () => {
        if (isSavedByUser) {
            setSavesCount(savesCount - 1);
            setIsSavedByUser(false);
            dispatch(unsavePost(post.postId, user.userId));
        }
        else {
            setSavesCount(savesCount + 1);
            setIsSavedByUser(true);
            dispatch(savePost(post.postId, user.userId));
        }
    };

    const handleOpenComments = () => {
        dispatch(fetchComments(post.postId));
        setIsCommentModalOpen(true);
    }

    const handleCloseComments = () => {
        setIsCommentModalOpen(false);
    }

    const handleAddComment = () => {
        if (newComment.trim()) {
            dispatch(addComment(post.postId, user.userId, newComment.trim()));
            setNewComment('');
            setCommentShowInput(false);
            setCommentCount(commentCount + 1);
        }
    }

    const handleEmojiSelect = (emoji) => {
        setNewComment((prev) => prev + emoji.native);
        setShowEmojiPicker(false);
    }

    const handleCloseEmojiPicker = (event) => {
        if (emojiPickerRef.current && !emojiPickerRef.current.contains(event.target))
            setShowEmojiPicker(false);
    };

    useEffect(() => {
        if (showEmojiPicker)
            document.addEventListener('mousedown', handleCloseEmojiPicker);
        else
            document.removeEventListener('mousedown', handleCloseEmojiPicker);
        return () => {
            document.removeEventListener('mousedown', handleCloseEmojiPicker);
        }
    }, [showEmojiPicker]);

    const updateCommentCount = () => {
        setCommentCount(commentCount + 1);
    }

    return (
        <S.PostContainer ref = { ref }>
            <S.PostHeader>
                <Avatar src = { post.user.profilePicture } alt = 'User DP' />
                <Box ml = { 2 }>
                    <Typography variant = 'subtitle1'>{ post.user.username }</Typography>
                    <Typography variant = 'caption' color = 'text.secondary'>{ new Date(createAt).toLocaleString() }</Typography>
                </Box>
            </S.PostHeader>
            { imageUrl && (
                <S.PostImage src = { imageUrl } alt = 'Post' />
            ) }
            <S.PostContent>
                <Typography variant = 'body1'>{ content }</Typography>
            </S.PostContent>
            <S.PostActions>
                <S.ActionsContainer>
                    <IconButton onClick = { handleLike }>
                        { isLikedByUser ? <Favorite style = {{ color: 'red' }} /> : <FavoriteBorder /> }
                    </IconButton>
                    <Typography variant = 'body2'>{ likeCount }</Typography>
                </S.ActionsContainer>
                <S.ActionsContainer>
                    <IconButton>
                        <ChatBubbleOutline />
                    </IconButton>
                    <Typography variant = 'body2'>{ commentCount }</Typography>
                </S.ActionsContainer>
                <S.ActionsContainer>
                    <IconButton onClick = { handleSave }>
                        { isSavedByUser ? <Bookmark style = {{ color: 'black' }} /> : <BookmarkBorder /> }
                    </IconButton>
                    <Typography variant = 'body2'>{ savesCount }</Typography>
                </S.ActionsContainer>
                <S.ActionsContainer>
                    <IconButton>
                        <Share />
                    </IconButton>
                </S.ActionsContainer>
            </S.PostActions>
            <Box>
                { commentCount > 0 && (
                    <S.CommentLinks onClick = { handleOpenComments }>
                        View all { commentCount } Comments
                    </S.CommentLinks>
                ) }
                { !showCommentInput ? (
                    <S.CommentLinks onClick = { () => setCommentShowInput(true) }>
                        Add a Comment...
                    </S.CommentLinks>
                ) : (
                    <S.PostAddCommentContainer>
                        <S.StyledTextField 
                            type = 'text'
                            value = { newComment }
                            onChange = { (e) => setNewComment(e.target.value) }
                            placeholder = 'Add a Comment...'
                            fullWidth
                            variant = 'outlined'
                            size = 'small'
                            onKeyDown = { (e) => e.key === 'Enter' && handleAddComment() }
                        />
                        { newComment.trim() && (
                            <S.AddCommentButton onClick = { handleAddComment }>
                                Post
                            </S.AddCommentButton>
                        ) }
                        <IconButton onClick = { () => setShowEmojiPicker(!showEmojiPicker) }>
                            <EmojiEmotions />
                        </IconButton>
                        { showEmojiPicker && (
                            <S.EmojiPickerContainer ref = { emojiPickerRef }>
                                <Picker data = { data } onEmojiSelect = { handleEmojiSelect } />
                            </S.EmojiPickerContainer>
                        ) }
                    </S.PostAddCommentContainer>
                ) }
            </Box>
            { isCommentModalOpen && <CommentsModal post = { post } onClose = { handleCloseComments } updateCommentCount = { updateCommentCount } /> }
        </S.PostContainer>
    )
});

export default Post;

// TODO: 
// 1. Do something about post createdAt