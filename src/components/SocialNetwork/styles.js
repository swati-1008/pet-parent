import { styled } from '@mui/material/styles';

export const LeftPanel = styled('div') (() => ({
    backgroundColor: 'red', 
    width: '0%', 
}));

export const Body = styled('div') (() => ({
    width: '80%', 
}));

export const RightPanel = styled('div') (() => ({
    float: 'right', 
    width: '20%', 
    display: 'flex', 
}));