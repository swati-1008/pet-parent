import React, { useState } from "react";
import * as S from './styles';
import { useDispatch, useSelector } from "react-redux";
import { createPost } from "../../redux/actions/createPostAction";
import { Avatar, Button } from "@mui/material";

const CreatePost = () => {
    const dispatch = useDispatch();
    const [postText, setPostText] = useState('');
    const user = useSelector((state) => state.auth.user);

    const handlePost = () => {
        if (postText.trim() === '') {
            alert('Post text cannot be empty');
            return;
        }
        const postData = {
            content: postText, 
            users: {
                userId: user.userId, 
            }
        };
        dispatch(createPost(postData));
        setPostText('');
    }

    return (
        <S.CreatePostContainer>
            <Avatar src = { user.profilePicture } alt = { user.username } />
            <S.StyledTextField 
                value = { postText }
                onChange = { (e) => setPostText(e.target.value) }
                placeholder = "What's on your mind..."
                fullWidth
                variant = 'outlined'
            />
            <Button variant = 'contained' color = 'primary' onClick = { handlePost }>Post</Button>
        </S.CreatePostContainer>
    );
};

export default CreatePost;

// TODO: 
// 1. When the post is saved successfully, refresh the feed and show the new post at top
// 2. Post should give option to upload photo/video