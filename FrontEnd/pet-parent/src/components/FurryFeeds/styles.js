import { Avatar, Box, Drawer, ListItem, ListItemButton, ListItemText, TextField } from "@mui/material";
import { styled } from "@mui/material/styles";

/* ------------- General -------------- */

export const BodyContainer = styled('div')(() => ({
    display: 'flex', 
    justifyContent: 'space-between', 
}));

export const SidePanel = styled(Drawer)(({ width }) => ({
    width: '240px', 
    flexShrink: 0, 
    '& .MuiDrawer-paper': {
        width: width, 
        boxSizing: 'border-box', 
        borderLeft: 'none', 
    }, 
}));

export const SidePanelHeadingContainer = styled('div')(() => ({
    background: `rgba(255, 255, 255, 0.2)`, 
    margin: '30px 30px 40px 30px', 
    fontWeight: "bold", 
}));

/* --------------- Left NavBar ------------------- */

export const LogoText = styled('span')(() => ({
    fontFamily: 'Leckerli One, sans-serif', 
    fontSize: '45px', 
}));

export const StyledNavMenu = styled(ListItemButton)(() => ({
    paddingLeft: '40px', 
    '&:hover': {
        backgroundColor: 'transparent', 
    }, 
}));

export const NavText = styled(ListItemText)(() => ({
    paddingLeft: '25px', 
    color: '#000', 
}));

/* ------------------ Right Panel ---------------- */

export const RightPanelListItem = styled(ListItem)(() => ({
    display: 'flex', 
    alignItems: 'center',
}));

export const StyledAvatar = styled(Avatar)(() => ({
    marginRight: 16, 
}));

export const HeadingText = styled('span')(() => ({
    fontFamily: 'Pacifico, sans-serif', 
    fontSize: '30px', 
}));

export const StyledListItemText = styled(ListItemText)({
    '& .MuiListItemText-primary': {
      fontWeight: 'bold',
    },
});

/* ----------------- Create Post ---------------- */

export const CreatePostContainer = styled(Box)(() => ({
    border: '1px solid #CCC', 
    padding: '16px', 
    display: 'flex', 
    alignItems: 'center', 
    justifyContent: 'center', 
    width: '45%', 
    margin: '70px auto 0 auto', 
    borderRadius: '8px', 
}));

export const StyledTextField = styled(TextField)(() => ({
    marginLeft: '16px', 
    marginRight: '16px', 
    flexGrow: 1, 
    '& .MuiOutlinedInput-root': {
        '& fieldset': {
            borderColor: 'transparent',
        },
        '&:hover fieldset': {
            borderColor: 'transparent',
        },
        '&.Mui-focused fieldset': {
            borderColor: 'transparent',
        },
    },
}));