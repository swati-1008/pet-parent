import React, { useEffect, useState } from "react";
import * as S from './styles';
import { Avatar, Box, IconButton, Typography } from "@mui/material";
import { Bookmark, BookmarkOutlined, ChatBubbleOutline, Favorite, FavoriteOutlined, Share } from "@mui/icons-material";
import { useDispatch, useSelector } from "react-redux";
import { fetchLikedPostsByUser, fetchSavedPostsByUser, likePost, savePost, unlikePost, unsavePost } from "../../redux/actions/postActions";

const Post = React.forwardRef(({ post }, ref) => {
    console.log('post ', post);
    const dispatch = useDispatch();
    const user = useSelector((state) => state.auth.user);
    const { likedPosts, savedPosts } = useSelector((state) => state.post);
    
    const [isLikedByUser, setIsLikedByUser] = useState(false);
    const [isSavedByUser, setIsSavedByUser] = useState(false);
    const [likeCount, setLikeCount] = useState(post.likeCount);
    const [savesCount, setSavesCount] = useState(post.savesCount);

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

    const { users, createAt, imageUrl, content, commentCount } = post;


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

    return (
        <S.PostContainer ref = { ref }>
            <S.PostHeader>
                <Avatar src = { users.profilePicture } alt = 'User DP' />
                <Box ml = { 2 }>
                    <Typography variant = 'subtitle1'>{ users.username }</Typography>
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
                        { isLikedByUser ? <Favorite style = {{ color: 'red' }} /> : <FavoriteOutlined /> }
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
                        { isSavedByUser ? <Bookmark /> : <BookmarkOutlined /> }
                    </IconButton>
                    <Typography variant = 'body2'>{ savesCount }</Typography>
                </S.ActionsContainer>
                <S.ActionsContainer>
                    <IconButton>
                        <Share />
                    </IconButton>
                </S.ActionsContainer>
            </S.PostActions>
        </S.PostContainer>
    )
});

export default Post;

// TODO: 
// 1. Do something about post createdAt