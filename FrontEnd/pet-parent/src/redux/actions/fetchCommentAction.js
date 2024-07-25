import axiosInstance from "../../axiosInstance";

export const FETCH_COMMENTS_REQUEST = 'FETCH_COMMENTS_REQUEST';
export const FETCH_COMMENTS_SUCCESS = 'FETCH_COMMENTS_SUCCESS';
export const FETCH_COMMENTS_FAILURE = 'FETCH_COMMENTS_FAILURE';
export const ADD_COMMENT_REQUEST = 'ADD_COMMENT_REQUEST';
export const ADD_COMMENT_SUCCESS = 'ADD_COMMENT_SUCCESS';
export const ADD_COMMENT_FAILURE = 'ADD_COMMENT_FAILURE';

const fetchCommentsRequest = () => ({
    type: FETCH_COMMENTS_REQUEST, 
});

const fetchCommentsSuccess = (comments) => ({
    type: FETCH_COMMENTS_SUCCESS, 
    payload: comments, 
});

const fetchCommentsFailure = (error) => ({
    type: FETCH_COMMENTS_FAILURE, 
    payload: error, 
});

const addCommentRequest = () => ({
    type: ADD_COMMENT_REQUEST, 
});

const addCommentSuccess = (comment) => ({
    type: ADD_COMMENT_SUCCESS, 
    payload: comment, 
});

const addCommentFailure = (error) => ({
    type: ADD_COMMENT_FAILURE, 
    payload: error, 
});

export const fetchComments = (postId) => async (dispatch) => {
    dispatch(fetchCommentsRequest());
    try {
        const response = await axiosInstance.post('/comments/get', { postId: postId });
        dispatch(fetchCommentsSuccess(response.data));
    }
    catch (error) {
        dispatch(fetchCommentsFailure(error.message));
    }
};

export const addComment = (postId, userId, commentText) => async (dispatch) => {
    dispatch(addCommentRequest());
    try {
        const response = await axiosInstance.post('/comments/add', { postId: postId, userId: userId, commentText: commentText });
        dispatch(addCommentSuccess(response.data));
    }
    catch (error) {
        dispatch(addCommentFailure(error));
    }
}
