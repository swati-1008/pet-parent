import React, {useRef, useState, useEffect } from "react";
import * as S from './styles';
import { useDispatch, useSelector } from "react-redux";
import { addComment } from "../../redux/actions/fetchCommentAction";
import { Close, EmojiEmotions } from "@mui/icons-material";
import { Avatar, Box, IconButton, Typography } from "@mui/material";
import Picker from "@emoji-mart/react";
import data from "@emoji-mart/data";

const CommentsModal = ({ post, onClose, updateCommentCount }) => {
    const dispatch = useDispatch();
    const comments = useSelector((state) => state.comments.comments);
    const user = useSelector((state) => state.auth.user);

    const [newComment, setNewComment] = useState('');
    const [showEmojiPicker, setShowEmojiPicker] = useState(false);

    const emojiPickerRef = useRef(null);

    const handleAddComment = () => {
        if (newComment.trim()) {
            dispatch(addComment(post.postId, user.userId, newComment.trim()));
            setNewComment('');
            updateCommentCount();
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

    return (
        <S.CommentModal 
            open
            onClose = { onClose }
        >
            <S.CommentPaper>
                <S.CloseButton onClick = { onClose }>
                    <Close style = {{ color: 'white', fontSize: 'xx-large' }} />
                </S.CloseButton>
                <S.CommentLeftModal>
                    { post.imageUrl && <S.PostImage src = { post.imageUrl } alt = 'Post' /> }
                </S.CommentLeftModal>
                <S.CommentRightModal>
                    <S.ModalHeader>
                        <Avatar src = { post.user.profilePicture } alt = 'User DP' />
                        <Box ml = { 2 }>
                            <Typography variant = 'subtitle1' fontWeight = 'bold'>{ post.user.username }</Typography>
                        </Box>
                    </S.ModalHeader>
                    <S.CommentsList>
                        { comments.map((comment) => (
                            <S.Comment key = { comment.commentId }>
                                <Avatar src = { comment.user.profilePicture } alt = 'User Profile' />
                                <Box ml = { 2 }>
                                    <Typography variant = 'subtitle1' fontWeight = 'bold'>{ comment.user.username }</Typography>
                                    <Box>{ comment.commentText }</Box>
                                </Box>
                            </S.Comment>
                        )) }
                    </S.CommentsList>
                    <S.CommentInputContainer>
                        <IconButton onClick = { () => setShowEmojiPicker(!showEmojiPicker) }>
                            <EmojiEmotions />
                        </IconButton>
                        { showEmojiPicker && (
                            <S.EmojiPickerContainer ref = { emojiPickerRef }>
                                <Picker data = { data } onEmojiSelect = { handleEmojiSelect } />
                            </S.EmojiPickerContainer>
                        ) }
                        <S.StyledTextField
                            type = 'text'
                            value = { newComment }
                            onChange = { (e) => setNewComment(e.target.value) }
                            placeholder = 'Add a comment...'
                            onKeyDown = { (e) => e.key === 'Enter' && handleAddComment() }
                            fullWidth
                            variant = 'outlined'
                        />
                        <S.AddCommentButton onClick = { handleAddComment } disabled = { !newComment.trim() }>
                            Post
                        </S.AddCommentButton>
                    </S.CommentInputContainer>
                </S.CommentRightModal>
            </S.CommentPaper>
        </S.CommentModal>
    )
};

export default CommentsModal;