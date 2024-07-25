import axiosInstance from "../../axiosInstance";

export const CREATE_POST_REQUEST = 'CREATE_POST_REQUEST';
export const CREATE_POST_SUCCESS = 'CREATE_POST_SUCCESS';
export const CREATE_POST_FAILURE = 'CREATE_POST_FAILURE';

const createPostRequest = () => ({
    type: CREATE_POST_REQUEST, 
});

const createPostSuccess = () => ({
    type: CREATE_POST_SUCCESS, 
});

const createPostFailure = (error) => ({
    type: CREATE_POST_FAILURE, 
    payload: error
});

export const createPost = (postData) => async (dispatch) => {
    dispatch(createPostRequest());
    try {
        const response = await axiosInstance.post('/post/create', postData);
        dispatch(createPostSuccess());
        console.log('Post created successfully', response);
    }
    catch (error) {
        dispatch(createPostFailure(error.message));
        console.log('Failed to create post');
    }
};