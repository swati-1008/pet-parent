import axiosInstance from "../../axiosInstance";

export const FETCH_SUGGESTIONS = 'FETCH_SUGGESTIONS';
export const FETCH_TRENDING_SUGGESTIONS = 'FETCH_TRENDING_SUGGESTIONS';

export const fetchSuggestions = (query) => {
    return async (dispatch) => {
        const response = await axiosInstance.post(`/suggestions/get`, { query: query });
        dispatch({
            type: FETCH_SUGGESTIONS, 
            payload: response.data, 
        });
    };
};

export const fetchTrendingSuggestions = () => {
    return async (dispatch) => {
        const response = await axiosInstance.post(`/suggestions/trending`);
        dispatch({
            type: FETCH_TRENDING_SUGGESTIONS, 
            payload: response.data, 
        });
    };
};

