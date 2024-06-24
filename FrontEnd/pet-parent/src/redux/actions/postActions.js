import axiosInstance from "../../axiosInstance";

export const FETCH_POSTS_REQUEST = 'FETCH_POSTS_REQUEST';
export const FETCH_POSTS_SUCCESS = 'FETCH_POSTS_SUCCESS';
export const FETCH_POSTS_FAILURE = 'FETCH_POSTS_FAILURE';
export const LIKE_POST = 'LIKE_POST';
export const UNLIKE_POST = 'UNLIKE_POST';
export const SAVE_POST = 'SAVE_POST';
export const UNSAVE_POST = 'UNSAVE_POST';
export const FETCH_LIKED_POSTS_BY_USER = 'FETCH_LIKED_POSTS_BY_USER';
export const FETCH_SAVED_POSTS_BY_USER = 'FETCH_SAVED_POSTS_BY_USER';

const fetchPostsRequest = () => ({
    type: FETCH_POSTS_REQUEST, 
});

const fetchPostsRequestSuccess = (posts) => ({
    type: FETCH_POSTS_SUCCESS, 
    payload: posts
});

const fetchPostsRequestFailure = (error) => ({
    type: FETCH_POSTS_FAILURE, 
    payload: error, 
});

const fetchLikePost = (postId, userId) => ({
    type: LIKE_POST, 
    payload: { postId, userId }, 
});

const fetchUnlikePost = (postId, userId) => ({
    type: UNLIKE_POST, 
    payload: { postId, userId }, 
});

const fetchSavePost = (postId, userId) => ({
    type: SAVE_POST, 
    payload: { postId, userId }, 
});

const fetchUnsavePost = (postId, userId) => ({
    type: UNSAVE_POST, 
    payload: { postId, userId }, 
});

const fetchLikedPostsByUserHelper = (data) => ({
    type: FETCH_LIKED_POSTS_BY_USER, 
    payload: data, 
});

const fetchSavedPostsByUserHelper = (data) => ({
    type: FETCH_SAVED_POSTS_BY_USER, 
    payload: data, 
});

export const fetchPosts = (page, limit) => async (dispatch) => {
    dispatch(fetchPostsRequest());
    try {
        const response = await axiosInstance.post('/post/all/page', { page: page, limit: limit });
        dispatch(fetchPostsRequestSuccess(response.data));
    }
    catch (error) {
        dispatch(fetchPostsRequestFailure(error.message));
    }
}

export const likePost = (postId, userId) => async (dispatch) => {
    try {
        await axiosInstance.post('/post/like', { postId: postId, userId: userId });
        dispatch(fetchLikePost(postId, userId));
    }
    catch (error) {
        console.error(error);
    }
};

export const unlikePost = (postId, userId) => async (dispatch) => {
    try {
        await axiosInstance.post('/post/unlike', { postId: postId, userId: userId });
        dispatch(fetchUnlikePost(postId, userId));
    }
    catch (error) {
        console.error(error);
    }
};

export const savePost = (postId, userId) => async (dispatch) => {
    try {
        await axiosInstance.post('/post/save', { postId: postId, userId: userId });
        dispatch(fetchSavePost(postId, userId));
    }
    catch (error) {
        console.error(error);
    }
};

export const unsavePost = (postId, userId) => async (dispatch) => {
    try {
        await axiosInstance.post('/post/unsave', { postId: postId, userId: userId });
        dispatch(fetchUnsavePost(postId, userId));
    }
    catch (error) {
        console.error(error);
    }
};

export const fetchLikedPostsByUser = (userId) => async (dispatch) => {
    try {
        const response = await axiosInstance.post('/post/user/liked', { userId: userId });
        dispatch(fetchLikedPostsByUserHelper(response.data));
    }
    catch (error) {
        console.error(error);
    }
};

export const fetchSavedPostsByUser = (userId) => async (dispatch) => {
    try {
        const response = await axiosInstance.post('/post/user/saved', { userId: userId });
        dispatch(fetchSavedPostsByUserHelper(response.data));
    }
    catch (error) {
        console.error(error);
    }
};