import { combineReducers } from 'redux';
import authReducer from './authReducer';
import fetchSuggestionsReducer from './fetchSuggestionsReducer';
import peopleYouMayKnowReducer from './peopleYouMayKnowReducer';
import { createPostReducer } from './createPostReducer';
import { postReducer } from './postReducer';
import { fetchCommentReducer } from './fetchCommentReducer';
import { reelReducer } from './reelReducer';

const rootReducer = combineReducers({
    auth: authReducer, 
    suggestions: fetchSuggestionsReducer, 
    peopleYouMayKnow: peopleYouMayKnowReducer, 
    createPost: createPostReducer, 
    post: postReducer, 
    comments: fetchCommentReducer, 
    reels: reelReducer, 
});

export default rootReducer;