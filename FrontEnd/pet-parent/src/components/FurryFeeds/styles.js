import { Avatar, Box, Button, Drawer, IconButton, ListItem, ListItemButton, ListItemText, Modal, TextField } from "@mui/material";
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
    width: '55%', 
    margin: '70px auto 0 200px', 
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

/* ----------------- Post ------------------ */

export const PostContainer = styled(Box)(() => ({
    border: '1px solid #DDD', 
    borderRadius: '10px', 
    overflow: 'hidden', 
    margin: '70px auto 16px 280px', 
    boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)', 
    backgroundColor: '#FFF', 
    width: '45%', 
}));

export const PostHeader = styled(Box)(() => ({
    display: 'flex', 
    alignItems: 'center', 
    padding: '10px', 
    borderBottom: '1px solid #DDD', 
}));

export const PostImage = styled('img')(() => ({
    width: '100%', 
    height: 'auto', 
    objectFit: 'cover', 
}));

export const PostContent = styled(Box)(() => ({
    padding: '10px', 
}));

export const PostActions = styled(Box)(() => ({
    display: 'flex', 
    justifyContent: 'space-between', 
    padding: '10px', 
    borderTop: '1px solid #DDD', 
}));

export const ActionsContainer = styled(Box)(() => ({
    display: 'flex', 
    alignItems: 'center', 
}));

export const PostAddCommentContainer = styled('div')(() => ({
    display: 'flex', 
    alignItems: 'center', 
    gap: '10px', 
    margin: '3px 0', 
}));

/* ---------------- Feed ----------------- */

export const FeedContainer = styled(Box)(() => ({
    marginTop: '50px', 
    marginRight: '240px', 
    width: `calc(100vw - 580px)`, 
    display: 'flex', 
    flexDirection: 'column', 
    alignItems: 'center', 
    gap: '16px', 
}));

/* -------------- Comments ----------------- */

export const CommentModal = styled(Modal)(() => ({
    display: 'flex', 
    alignItems: 'center', 
    justifyContent: 'center', 
}));

export const CommentPaper = styled('div')(() => ({
    backgroundColor: '#FFF', 
    boxShadow: 5, 
    padding: '16px 32px 24px', 
    display: 'flex', 
    flexDirection: 'row', 
    width: '80%', 
    height: '80%', 
}));

export const CloseButton = styled(IconButton)(() => ({
    cursor: 'pointer', 
    position: 'absolute', 
    top: '8px', 
    right: '8px', 
}));

export const CommentLeftModal = styled('div')(() => ({
    width: '50%', 
    padding: '16px', 
    borderRight: '1px solid #CCC', 
    display: 'flex', 
    flexDirection: 'column', 
    justifyContent: 'center', 
    textAlign: 'justify', 
}));

export const CommentRightModal = styled('div')(() => ({
    width: '50%', 
    padding: '16px', 
    display: 'flex', 
    flexDirection: 'column', 
    position: 'relative', 
    overflow: 'hidden', 
}));

export const ModalHeader = styled(Box)(() => ({
    display: 'flex', 
    alignItems: 'center', 
    padding: '10px', 
    borderBottom: '1px solid #DDD', 
}));

export const CommentInputContainer = styled('div')(() => ({
    display: 'flex', 
    alignItems: 'center', 
    borderTop: '1px solid #CCC', 
    paddingTop: '8px', 
    position: 'absolute', 
    bottom: 0, 
    width: '97%', 
}));

export const CommentsList = styled('div')(() => ({
    flexGrow: 1, 
    overflowY: 'auto', 
    marginBottom: '50px', 
}));

export const Comment = styled('div')(() => ({
    display: 'flex', 
    marginTop: '30px', 
    marginBottom: '10px', 
    marginLeft: '10px', 
}));

export const CommentInput = styled(TextField)(() => ({
    flexGrow: 1, 
    border: 'none', 
    outline: 'none', 
    padding: '8px', 
}));

export const AddCommentButton = styled(Button)(() => ({
    marginLeft: '8px', 
    fontWeight: 'bold', 
    cursor: 'pointer', 
    padding: '8px 16px', 
    borderRadius: '4px', 
}));

export const CommentLinks = styled('span')(() => ({
    cursor: 'pointer', 
    color: 'gray', 
    fontWeight: 'bold', 
    display: 'block', 
    marginBottom: '10px', 
    marginLeft: '10px', 
    fontSize: '14px', 
    '&:hover': {
        textDecoration: 'underline', 
    }, 
}));