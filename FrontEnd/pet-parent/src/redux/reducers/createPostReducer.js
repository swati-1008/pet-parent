import { CREATE_POST_REQUEST, CREATE_POST_SUCCESS, CREATE_POST_FAILURE } from "../actions/createPostAction";

const initialState = {
    loading: false, 
    error: null, 
};

export const createPostReducer = (state = initialState, action) => {
    switch(action.type) {
        case CREATE_POST_REQUEST: 
            return {
                ...state, 
                loading: true, 
            };
        case CREATE_POST_SUCCESS: 
            return {
                ...state, 
                loading: false, 
            };
        case CREATE_POST_FAILURE: 
            return {
                ...state, 
                loading: false, 
                error: action.payload, 
            };
        default: 
            return state;
    }
};