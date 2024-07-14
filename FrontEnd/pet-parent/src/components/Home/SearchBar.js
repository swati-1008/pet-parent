import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { fetchSuggestions, fetchTrendingSuggestions } from "../../redux/actions/fetchSuggestionsActions";
import { Paper, List, ListItem, TextField, Typography } from '@mui/material';
import * as S from './styles';

const SearchBar = () => {
    const [query, setQuery] = useState('');
    const [isFocussed, setIsFocussed] = useState(false);
    const dispatch = useDispatch();

    const suggestions = useSelector((state) => state.suggestions.suggestions);
    const trendingSuggestions = useSelector((state) => state.suggestions.trendingSuggestions);

    useEffect(() => {
        dispatch(fetchTrendingSuggestions())
    }, [dispatch]);

    const handleInputChange = (e) => {
        const newQuery = e.target.value;
        setQuery(newQuery);
        if (newQuery)
            dispatch(fetchSuggestions(newQuery));
    };

    const handleFocus = () => {
        setIsFocussed(true);
    }

    const handleBlur = () => {
        setIsFocussed(false);
    }

    const renderSuggestions = (suggestions) => {
        return <Paper style={{ position: 'absolute', width: '100%', zIndex: 999 }}>
            <List>
                { suggestions?.map((suggestion, index) => (
                    <ListItem key={index}>{ suggestion.suggestion }</ListItem>
                )) }
            </List>
        </Paper>
    };

    return (
        <S.SearchBar>
            <TextField 
                fullWidth
                variant = 'outlined'
                value = { query }
                onChange = { handleInputChange }
                onFocus = { handleFocus }
                onBlur = { handleBlur }
                placeholder = "Sniff and Seek"
                autoComplete = 'off'
                sx = {{
                    '& .MuiOutlinedInput-root': {
                        borderRadius: '50px', 
                    }, 
                }}
            />
            { isFocussed && query === '' && (
                <>
                    <Typography variant = 'body2' color = 'textSecondary'>
                        Trending: 
                    </Typography>
                    { renderSuggestions(trendingSuggestions) }
                </>
            ) }
            { isFocussed && query !== '' && renderSuggestions(suggestions) }
        </S.SearchBar>
    );
}

export default SearchBar;


// Future Scope (TODO:): 
// 1. Save user searches and get trending suggestions dynamically, based on what was searched most