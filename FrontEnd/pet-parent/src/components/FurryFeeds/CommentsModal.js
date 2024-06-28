import React, {useState } from "react";
import * as S from './styles';
import { useDispatch, useSelector } from "react-redux";
import { addComment } from "../../redux/actions/fetchCommentAction";
import { Close } from "@mui/icons-material";
import { Avatar, Box, Typography } from "@mui/material";

const CommentsModal = ({ post, onClose }) => {
    const dispatch = useDispatch();
    const comments = useSelector((state) => state.comments.comments);
    const user = useSelector((state) => state.auth.user);

    const [newComment, setNewComment] = useState('');

    const handleAddComment = () => {
        if (newComment.trim()) {
            dispatch(addComment(post.postId, user.userId, newComment.trim()));
            setNewComment('');
        }
    }

    return (
        <S.CommentModal 
            open
            onClose = { onClose }
        >
            <S.CommentPaper>
                <S.CloseButton onClick = { onClose }>
                    <Close />
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

// TODO: 
// 1. Add an Emoji Selector 
// 4. Modal close button