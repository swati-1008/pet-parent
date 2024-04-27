import { Card } from '@mui/material';
import { styled } from '@mui/material/styles';

export const StyledCard = styled(Card)(() => ({
    marginLeft: "620px", 
    marginTop: "50px", 
    width: "450px", 
    height: "500px", 
}));

export const ActionsBar = styled('div')(() => ({
    display: "flex", 
    alignItems: "center", 
    justifyContent: "space-around", 
    marginTop: "10px", 
}));

export const TitleBar = styled('div')(() => ({
    height: "50px", 
    margin: "10px 0 0 10px", 
}));

export const UserHandle = styled('span')(() => ({
    display: "inline-block", 
    paddingLeft: "20px", 
    verticalAlign: "15px", 
    fontWeight: "bold", 
}));

export const PostedTime = styled('small')(() => ({
    display: 'inline-block', 
    paddingLeft: '10px', 
    verticalAlign: '15px', 
    fontStyle: 'italic', 
}));

export const Content = styled('div')(() => ({
    textAlign: 'center', 
    marginTop: '5px', 
}));

export const ContentImage = styled('img')(() => ({
    width: "430px", 
    height: "370px", 
}));

export const Actions = styled('div')(() => ({
    display: 'inline-block', 
}));

export const Count = styled('div')(() => ({
    display: 'inline-block', 
    verticalAlign: '5px', 
    paddingLeft: '5px', 
}));