import { combineReducers } from 'redux';
import authReducer from './authReducer';
import fetchSuggestionsReducer from './fetchSuggestionsReducer';
import peopleYouMayKnowReducer from './peopleYouMayKnowReducer';

const rootReducer = combineReducers({
    auth: authReducer, 
    suggestions: fetchSuggestionsReducer, 
    peopleYouMayKnow: peopleYouMayKnowReducer, 
});

export default rootReducer;