import { configureStore } from '@reduxjs/toolkit';
import rootReducer from './reducers';
import { initialState } from './reducers/authReducer';
import { thunk } from 'redux-thunk';

let preloadedState = initialState;

const store = configureStore({
    reducer: rootReducer, 
    preloadedState: preloadedState, 
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(thunk), 
});

export default store;