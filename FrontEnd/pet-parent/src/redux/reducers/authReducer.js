import { LOGIN_SUCCESS, SIGNUP_SUCCESS, CHECK_USERNAME_SUCCESS, AUTH_ERROR, LOGOUT } from '../actions/authAction';

export const initialState = {
    isAuthenticated: !!localStorage.getItem('token'), 
    usernameExists: false, 
    error: null, 
    user: null, 
};

const authReducer = (state = initialState, action) => {
    switch(action.type) {
        case LOGIN_SUCCESS: 
            localStorage.setItem('token', action.payload.token);
            return {
                ...state, 
                isAuthenticated: true, 
                error: null, 
                user: action.payload.user, 
            };
        case SIGNUP_SUCCESS: 
            return {
                ...state, 
                isAuthenticated: false, 
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
        case LOGOUT: 
            localStorage.removeItem('token');
            return {
                ...state, 
                isAuthenticated: false, 
                user: null, 
            };
        default: return state;
    }
};

export default authReducer;