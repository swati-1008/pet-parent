import axiosInstance from "../../axiosInstance";

export const FETCH_REELS_REQUEST = 'FETCH_REELS_REQUEST';
export const FETCH_REELS_SUCCESS = 'FETCH_REELS_SUCCESS';
export const FETCH_REELS_FAILURE = 'FETCH_REELS_FAILURE';

const fetchReelsRequest = () => ({
    type: FETCH_REELS_REQUEST, 
});

const fetchReelsSuccess = (reels) => ({
    type: FETCH_REELS_SUCCESS, 
    payload: reels, 
});

const fetchReelsFailure = (error) => ({
    type: FETCH_REELS_FAILURE, 
    payload: error, 
});

export const fetchReels = () => async (dispatch) => {
    dispatch(fetchReelsRequest());
    try {
        const response = await axiosInstance.post('/reels/all');
        dispatch(fetchReelsSuccess(response.data));
    }
    catch (error) {
        dispatch(fetchReelsFailure(error.message));
    }
};