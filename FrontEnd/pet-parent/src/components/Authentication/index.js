import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { checkUsername, login, signUp } from '../../redux/actions/authAction';
import * as S from './styles';
import backgroundImage from '../../assets/images/background.png';
import paw from '../../assets/images/paw.png';
import { TextField, Button, Typography } from '@mui/material';

const Authentication = () => {
    const dispatch = useDispatch();
    const { error, usernameExists } = useSelector(state => state.auth);

    const [formData, setFormData] = useState({
        username: '', 
        email: '', 
        password: '', 
        confirmPassword: '', 
    });

    const [isSigningUp, setIsSigningUp] = useState(false);

    useEffect(() => {
        if (formData.username)
            dispatch(checkUsername(formData.username))
    }, [formData.username, dispatch]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData, 
            [ name ] : value, 
        }));
    };

    const handleAuth = (e) => {
        e.preventDefault();
        if (isSigningUp) {
            if (formData.password === formData.confirmPassword)
                dispatch(signUp({ formData }));
            else 
                alert('Passwords do not match');
        }
        else {
            if (usernameExists)
                dispatch(login({ username: formData.username, email: formData.email, password: formData.password }));
            else 
                alert('Username does not exist. Please sign up');
        }
    }

    const toggleSignUp = () => {
        setIsSigningUp(!isSigningUp);
        setFormData({
            username: '', 
            email: '', 
            password: '', 
            confirmPassword: '', 
        });
    };

    return (
        <S.StyledBackground>
            <S.BackgroundImage src = { backgroundImage } />
            <S.PawPrintContainer>
                <S.PawPrint src = { paw } size = '300px' top = '-7%' left = '-3%' />
                <S.PawPrint src = { paw } size = '200px' top = '85%' left = '30%' />
            </S.PawPrintContainer>
            <S.FormContainer>
                <S.FormCard>
                    <Typography variant = 'h5' component = 'div' gutterBottom>
                        { isSigningUp ? 'Sign Up' : 'Sign In' }
                    </Typography>
                    <S.Form onSubmit = { handleAuth }>
                        <TextField 
                            label = "Username"
                            name = 'username'
                            onChange = { handleChange }
                            required
                            variant = 'outlined'
                            color = 'secondary'
                            type = 'text'
                            fullWidth
                            value = { formData.username }
                            margin = 'normal'
                            error = { isSigningUp && usernameExists }
                        />
                        { isSigningUp && (
                            <TextField 
                                label = "Email"
                                name = 'email'
                                onChange = { handleChange }
                                required
                                variant = 'outlined'
                                color = 'secondary'
                                type = 'email'
                                fullWidth
                                value = { formData.email }
                                margin = 'normal'
                            />
                        ) }
                        <TextField 
                            label = "Password"
                            name = 'password'
                            onChange = { handleChange }
                            required
                            variant = 'outlined'
                            color = 'secondary'
                            type = 'password'
                            fullWidth
                            value = { formData.password }
                            margin = 'normal'
                        />
                        { isSigningUp && (
                            <TextField 
                                label = "Confirm Password"
                                name = 'confirmPassword'
                                onChange = { handleChange }
                                required
                                variant = 'outlined'
                                color = 'secondary'
                                type = 'password'
                                fullWidth
                                value = { formData.confirmPassword }
                                margin = 'normal'
                            />
                        ) }
                        <Button 
                            type = 'submit' 
                            variant = 'contained'
                            fullWidth
                        >
                            { isSigningUp ? 'Sign Up' : 'Sign In' }
                        </Button>
                    </S.Form>
                    <S.LinkText>
                        { isSigningUp ? (
                            <>
                                Already have an account? { ' ' }
                                <S.Link onClick = { toggleSignUp }>
                                    Sign In
                                </S.Link>
                            </>
                        ) : (
                            <>
                                Don't have an account? { ' ' }
                                <S.Link onClick = { toggleSignUp }>
                                    Sign Up
                                </S.Link>
                            </>
                        ) }
                    </S.LinkText>
                    { error && <p style = {{ color: 'red', textAlign: 'center' }}>{ error }</p> }
                </S.FormCard>
            </S.FormContainer>
        </S.StyledBackground>
    );
};

export default Authentication;


// TODO: 
// 1. Add paw prints inside card
// 2. Use formData instead of different username, email states
// 3. After Sign Up successful, automatically redirect to Sign In and vice versa
// 4. Password Encryption