import { FETCH_REELS_REQUEST, FETCH_REELS_SUCCESS, FETCH_REELS_FAILURE } from "../actions/reelAction";

const initialState = {
    loading: false, 
    reels: [], 
    error: null, 
};

export const reelReducer = (state = initialState, action) => {
    switch(action.type) {
        case FETCH_REELS_REQUEST: 
            return {
                ...state, 
                loading: true, 
            };
        case FETCH_REELS_SUCCESS: 
            return {
                ...state, 
                loading: false, 
                reels: action.payload, 
            };
        case FETCH_REELS_FAILURE: 
            return {
                ...state, 
                loading: false, 
                error: action.payload, 
            };
        default: 
            return state;
    }
};