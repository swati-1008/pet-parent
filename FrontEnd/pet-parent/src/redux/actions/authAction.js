import axiosInstance from '../../axiosInstance';

export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const SIGNUP_SUCCESS = 'SIGNUP_SUCCESS';
export const CHECK_USERNAME_SUCCESS = 'CHECK_USERNAME_SUCCESS';
export const AUTH_ERROR = 'AUTH_ERROR';
export const LOGOUT = 'LOGOUT';

export const loginSuccess = (token, user) => ({
    type: LOGIN_SUCCESS, 
    payload: { token, user }, 
});

export const signUpSuccess = () => ({
    type: SIGNUP_SUCCESS,
});

export const logout = () => ({
    type: LOGOUT, 
});

export const checkUsernameSuccess = (exists) => ({
    type: CHECK_USERNAME_SUCCESS, 
    payload: exists, 
});

export const authError = (error) => ({
    type: AUTH_ERROR, 
    payload: error, 
});

export const login = (credentials) => async (dispatch) => {
    try {
        const response = await axiosInstance.post('/user/login', credentials);
        if (response.data.token && response.data.user) {
            localStorage.setItem('token', response.data.token);
            dispatch(loginSuccess(response.data.token, response.data.user))
            return Promise.resolve();
        }
        else {
            dispatch(authError('Invalid Credentials'));
            return Promise.reject();
        }
    }
    catch (error) {
        dispatch(authError(error.response ? error.response.data.message : 'Network Error'));
        return Promise.reject();
    }
};

export const signUp = (userDetails) => async (dispatch) => {
    try {
        const response = await axiosInstance.post('/user/signup', userDetails.formData);
        if (response.status === 200) {
            dispatch(signUpSuccess());
            return Promise.resolve();
        }
        else {
            dispatch(authError(response.data.message));
            return Promise.reject();
        }
    }
    catch (error) {
        dispatch(authError(error.response ? error.response.data.message : 'Network Error'));
        return Promise.reject();
    }
};

export const checkUsername = (username) => async (dispatch) => {
    try {
        const response = await axiosInstance.post('/user/all');
        const userExists = response.data.some(user => user.username === username)
        dispatch(checkUsernameSuccess(userExists));
    }
    catch (error) {
        dispatch(authError(error.response ? error.response.data.message : 'Network Error'));
    }
}