import axiosInstance from '../../axiosInstance';

export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const SIGNUP_SUCCESS = 'SIGNUP_SUCCESS';
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

export const authError = (error) => ({
    type: AUTH_ERROR, 
    payload: error, 
});

export const login = (credentials) => async (dispatch) => {
    try {
        const response = await axiosInstance.post('/user/login', credentials);
        if (response.data.id_token) {
            localStorage.setItem('token', response.data.id_token);
            const users = await axiosInstance.post('/user/getByName', { username: credentials.username });
            dispatch(loginSuccess(response.data.id_token, users.data))
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