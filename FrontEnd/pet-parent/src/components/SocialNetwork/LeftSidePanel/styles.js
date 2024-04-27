import { styled } from '@mui/material/styles';
import { ListItemIcon } from '@mui/material';

export const StyledLogo = styled('div')(() => ({
    background: `rgba(255, 255, 255, 0.2)`, 
    margin: "30px 30px 20px 30px", 
    fontWeight: "bold", 
}));

export const StyledFontLogo = styled('span')(() => ({
    fontFamily: `Sacramento, sans-serif`, 
    fontSize: "45px", 
}));

export const StyledListIcon = styled(ListItemIcon)(() => ({
    marginRight: "-10px", 
    marginLeft: "20px", 
}))