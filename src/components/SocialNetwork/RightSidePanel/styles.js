import { styled } from '@mui/material/styles';
import { ListItemIcon } from '@mui/material';

export const StyledLogo = styled('div')(() => ({
    background: `rgba(255, 255, 255, 0.2)`, 
    margin: "90px 0px 20px 30px", 
    fontWeight: "bold", 
}));

export const StyledFontLogo = styled('span')(() => ({
    fontFamily: `Roboto, sans-serif`, 
    fontSize: "22px", 
}));

export const StyledListIcon = styled(ListItemIcon)(() => ({
    marginRight: "-10px", 
    marginLeft: "20px", 
}));

export const UserHandle = styled('span')(() => ({
    // display: "inline-block", 
    paddingLeft: "20px", 
    verticalAlign: "15px", 
    fontWeight: "bold", 
}));

export const SuggestedPeople = styled('div')(() => ({
    display: 'block', 
    marginBottom: '8px', 
}))