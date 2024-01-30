import React from 'react';
import Post from '../Post/Post';
import postList from './postList';

const Posts = () => {
    return (
        <>
            { postList.map((post, index) => {
                return <Post post={post} key={index} />
            }) }
        </>
    )
}

export default Posts;