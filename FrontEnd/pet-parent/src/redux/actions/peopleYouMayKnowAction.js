import axiosInstance from "../../axiosInstance";

export const FETCH_SUGGESTED_PEOPLE_REQUEST = 'FETCH_SUGGESTED_PEOPLE_REQUEST';
export const FETCH_SUGGESTED_PEOPLE_SUCCESS = 'FETCH_SUGGESTED_PEOPLE_SUCCESS';
export const FETCH_SUGGESTED_PEOPLE_FAILURE = 'FETCH_SUGGESTED_PEOPLE_FAILURE';

export const fetchSuggestedPeopleRequest = () => ({
    type: FETCH_SUGGESTED_PEOPLE_REQUEST, 
});

export const fetchSuggestedPeopleSuccess = (people) => ({
    type: FETCH_SUGGESTED_PEOPLE_SUCCESS, 
    payload: people
});

export const fetchSuggestedPeopleFailure = (error) => ({
    type: FETCH_SUGGESTED_PEOPLE_FAILURE, 
    payload: error, 
});

export const fetchSuggestedPeople = (userId) => {
    return async (dispatch) => {
        dispatch(fetchSuggestedPeopleRequest());
        try {
            const response = await axiosInstance.post('/follow/suggestions', { userId: userId });
            dispatch(fetchSuggestedPeopleSuccess(response.data));
        }
        catch (error) {
            dispatch(fetchSuggestedPeopleFailure(error.message));
        }
    };
};