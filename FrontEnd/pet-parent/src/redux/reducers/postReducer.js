import { FETCH_POSTS_REQUEST, FETCH_POSTS_SUCCESS, FETCH_POSTS_FAILURE, 
    LIKE_POST, UNLIKE_POST, SAVE_POST, UNSAVE_POST, FETCH_LIKED_POSTS_BY_USER, 
    FETCH_SAVED_POSTS_BY_USER } from "../actions/postActions";

const initialState = {
    loading: false, 
    data: { content: [] }, 
    error: null, 
    likedPosts: [], 
    savedPosts: [], 
};

export const postReducer = (state = initialState, action) => {
    switch(action.type) {
        case FETCH_POSTS_REQUEST: 
            return {
                ...state, 
                loading: true, 
            };
        case FETCH_POSTS_SUCCESS: 
            return {
                ...state, 
                loading: false, 
                data: {
                    ...action.payload, 
                    content: [...state.data.content, action.payload.content], 
                }, 
            };
        case FETCH_POSTS_FAILURE: 
            return {
                ...state, 
                loading: false, 
                error: action.payload, 
            };
        case LIKE_POST: 
            return {
                ...state, 
                posts: state.posts.map(
                    (post) => 
                        post.postId === action.payload.postId 
                        ? { ...post, likeCount: post.likeCount + 1 } 
                        : post
                ), 
                likedPosts: [...state.likedPosts, action.payload], 
            };
        case UNLIKE_POST: 
            return {
                ...state, 
                posts: state.posts.map(
                    (post) => 
                        post.postId === action.payload.postId 
                        ? { ...post, likeCount: post.likeCount - 1 } 
                        : post
                ), 
                likedPosts: state.likedPosts.filter((post) => post.postId !== action.payload.postId), 
            };
        case SAVE_POST: 
            return {
                ...state, 
                posts: state.posts.map(
                    (post) => 
                        post.postId === action.payload.postId 
                        ? { ...post, savesCount: post.savesCount + 1 } 
                        : post
                ), 
                savedPosts: [...state.savedPosts, action.payload], 
            };
        case UNSAVE_POST: 
            return {
                ...state, 
                posts: state.posts.map(
                    (post) => 
                        post.postId === action.payload.postId 
                        ? { ...post, savesCount: post.savesCount - 1 } 
                        : post
                ), 
                savedPosts: state.savedPosts.filter((post) => post.postId !== action.payload.postId), 
            };
        case FETCH_LIKED_POSTS_BY_USER: 
            return {
                ...state, 
                likedPosts: action.payload, 
            };
        case FETCH_SAVED_POSTS_BY_USER: 
            return {
                ...state, 
                savedPosts: action.payload, 
            };
        default: 
            return state;
    }
};