import React, { useCallback, useEffect, useRef, useState } from "react";
import * as S from './styles';
import { useDispatch, useSelector } from "react-redux";
import { fetchPosts } from "../../redux/actions/postActions";
import { Typography } from "@mui/material";
import Post from "./Post";

const Feed = () => {
    const dispatch = useDispatch();
    const postsState = useSelector((state) => state.post.data);
    const { loading } = useSelector((state) => state.post);
    const [page, setPage] = useState(0);
    const observer = useRef();

    useEffect(() => {
        dispatch(fetchPosts(page, 10));
    }, [dispatch, page]);

    const lastPostRef = useCallback((node) => {
        if (loading)
            return;
        if (observer.current)
            observer.current.disconnect();
        observer.current = new IntersectionObserver((entries) => {
            if (entries[0].isIntersecting)
                setPage((prevPage) => prevPage + 1);
        });
        if (node)
            observer.current.observe(node);
    }, [loading]);

    const posts = postsState ? postsState.content : [];

    return (
        <S.FeedContainer>
            { posts.map((post, index) => {
                if (posts.length === index + 1)
                    return post.map((item) => <Post ref = { lastPostRef } key = { item.postId } post = { item } />)
                else
                    return post.map((item) => <Post key = { item.postId } post = { item } />)
            }) }
            { loading && <Typography>Loading</Typography> }
        </S.FeedContainer>
    )
};

export default Feed;