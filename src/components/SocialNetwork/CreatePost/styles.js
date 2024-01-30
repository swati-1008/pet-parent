import { styled } from '@mui/material/styles';
import { Card, Avatar, TextField, Button } from '@mui/material';

export const StyledCard = styled(Card)(() => ({
    borderRadius: "10px", 
    margin: "100px 40px 0px 300px", 
}));

export const StyledAvatar = styled(Avatar)(() => ({
    display: "inline-block", 
    marginRight: "25px", 
}));

export const StyledTextField = styled(TextField)(() => ({
    width: "1200px", 
}));

export const StyledSubmit = styled(Button)(() => ({
    marginRight: "20px", 
    float: "right", 
    height: '40px', 
}))