import { LOGIN_SUCCESS, SIGNUP_SUCCESS, CHECK_USERNAME_SUCCESS, AUTH_ERROR } from '../actions/authAction';

export const initialState = {
    isAuthenticated: false, 
    usernameExists: false, 
    error: null, 
};

const authReducer = (state = initialState, action) => {
    switch(action.type) {
        case LOGIN_SUCCESS: 
            return {
                ...state, 
                isAuthenticated: true, 
                error: null, 
            };
        case SIGNUP_SUCCESS: 
            return {
                ...state, 
                isAuthenticated: true, 
                error: null, 
            };
        case CHECK_USERNAME_SUCCESS: 
            return {
                ...state, 
                usernameExists: action.payload, 
                error: null, 
            };
        case AUTH_ERROR: 
            return {
                ...state, 
                error: action.payload, 
            };
        default: return state;
    }
};

export default authReducer;