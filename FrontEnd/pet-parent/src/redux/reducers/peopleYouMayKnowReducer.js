import { FETCH_SUGGESTED_PEOPLE_REQUEST, FETCH_SUGGESTED_PEOPLE_SUCCESS, FETCH_SUGGESTED_PEOPLE_FAILURE } from "../actions/peopleYouMayKnowAction";

const initialState = {
    loading: false, 
    suggestedPeople: [], 
    error: '', 
};

const suggestedPeopleReducer = (state = initialState, action) => {
    switch(action.type) {
        case FETCH_SUGGESTED_PEOPLE_REQUEST: 
            return {
                ...state, 
                loading: true, 
            };
        case FETCH_SUGGESTED_PEOPLE_SUCCESS: 
            return {
                ...state, 
                loading: false, 
                suggestedPeople: action.payload, 
                error: '', 
            };
        case FETCH_SUGGESTED_PEOPLE_FAILURE: 
            return {
                ...state, 
                loading: false, 
                suggestedPeople: [], 
                error: action.payload, 
            };
        default: 
            return state;
    }
}

export default suggestedPeopleReducer;