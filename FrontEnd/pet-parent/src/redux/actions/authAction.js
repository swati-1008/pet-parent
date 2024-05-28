import axiosInstance from '../../axiosInstance';

export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const SIGNUP_SUCCESS = 'SIGNUP_SUCCESS';
export const CHECK_USERNAME_SUCCESS = 'CHECK_USERNAME_SUCCESS';
export const AUTH_ERROR = 'AUTH_ERROR';

export const loginSuccess = () => ({
    type: LOGIN_SUCCESS, 
});

export const signUpSuccess = () => ({
    type: SIGNUP_SUCCESS,
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
        if (response.data)
            dispatch(loginSuccess())
        else
            dispatch(authError('Invalid Credentials'));
    }
    catch (error) {
        dispatch(authError(error.response ? error.response.data.message : 'Network Error'));
    }
};

export const signUp = (userDetails) => async (dispatch) => {
    try {
        const response = await axiosInstance.post('/user/signup', userDetails.formData);
        if (response.data.success)
            dispatch(signUpSuccess());
        else
            dispatch(authError(response.data.message));
    }
    catch (error) {
        dispatch(authError(error.response ? error.response.data.message : 'Network Error'));
    }
};

export const checkUsername = (username) => async (dispatch) => {
    try {
        const response = await axiosInstance.get('/user/all');
        const userExists = response.data.some(user => user.username === username)
        dispatch(checkUsernameSuccess(userExists));
    }
    catch (error) {
        dispatch(authError(error.response ? error.response.data.message : 'Network Error'));
    }
}