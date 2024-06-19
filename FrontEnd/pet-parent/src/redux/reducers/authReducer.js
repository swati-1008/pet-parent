import { LOGIN_SUCCESS, SIGNUP_SUCCESS, CHECK_USERNAME_SUCCESS, AUTH_ERROR, LOGOUT } from '../actions/authAction';

export const initialState = {
    isAuthenticated: !!localStorage.getItem('user'), 
    usernameExists: false, 
    error: null, 
    user: JSON.parse(localStorage.getItem('user')) || null, 
};

const authReducer = (state = initialState, action) => {
    switch(action.type) {
        case LOGIN_SUCCESS: 
            localStorage.setItem('user', JSON.stringify(action.payload));
            return {
                ...state, 
                isAuthenticated: true, 
                error: null, 
                user: action.payload, 
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
            localStorage.removeItem('user');
            return {
                ...state, 
                isAuthenticated: false, 
                user: null, 
            };
        default: return state;
    }
};

export default authReducer;