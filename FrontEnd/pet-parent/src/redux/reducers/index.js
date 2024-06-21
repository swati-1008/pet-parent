import { combineReducers } from 'redux';
import authReducer from './authReducer';
import fetchSuggestionsReducer from './fetchSuggestionsReducer';
import peopleYouMayKnowReducer from './peopleYouMayKnowReducer';
import { createPostReducer } from './createPostReducer';

const rootReducer = combineReducers({
    auth: authReducer, 
    suggestions: fetchSuggestionsReducer, 
    peopleYouMayKnow: peopleYouMayKnowReducer, 
    createPost: createPostReducer, 
});

export default rootReducer;