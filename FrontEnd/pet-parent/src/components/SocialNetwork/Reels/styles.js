import { styled } from '@mui/material/styles';

export const ReelDiv = styled('img')((props) => ({
    margin: '40px 0px 0px 400px', 
    borderRadius: '200px', 
    width: '100px', 
    height: '100px', 
    border: '5px solid', 
    borderColor: props.seen, 
    padding: '2px', 
}));