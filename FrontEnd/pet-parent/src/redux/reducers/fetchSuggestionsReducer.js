import { FETCH_SUGGESTIONS, FETCH_TRENDING_SUGGESTIONS } from "../actions/fetchSuggestionsActions";

const initialState = {
    suggestions: [], 
    trendingSuggestions: [], 
};

const fetchSuggestionsReducer = (state = initialState, action) => {
    switch(action.type) {
        case FETCH_SUGGESTIONS: 
            return {
                ...state, 
                suggestions: action.payload, 
            };
        case FETCH_TRENDING_SUGGESTIONS: 
            return {
                ...state, 
                trendingSuggestions: action.payload, 
            };
        default: return state;
    }
};

export default fetchSuggestionsReducer;