import { combineReducers } from 'redux';
import authReducer from './authReducer';
import fetchSuggestionsReducer from './fetchSuggestionsReducer';

const rootReducer = combineReducers({
    auth: authReducer, 
    suggestions: fetchSuggestionsReducer, 
});

export default rootReducer;