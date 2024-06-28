import React from "react";
import * as S from './styles';
import { AccountCircle, Bookmark, ChatBubbleOutline, Explore, Home, Notifications, Search, SlowMotionVideo } from "@mui/icons-material";
import { List, ListItem } from "@mui/material";

const LeftNavBar = () => {
    const navMenu = [
        { text: 'Home', icon: <Home /> }, 
        { text: 'Search', icon: <Search /> }, 
        { text: 'Explore', icon: <Explore /> }, 
        { text: 'Reels', icon: <SlowMotionVideo /> }, 
        { text: 'Notifications', icon: <Notifications /> }, 
        { text: 'Messages', icon: <ChatBubbleOutline /> }, 
        { text: 'Profile', icon: <AccountCircle /> }, 
        { text: 'Saved', icon: <Bookmark /> }, 
    ];

    return (
        <S.SidePanel 
            anchor = 'left'
            variant = 'permanent'
            width = '240px'
        >
            <S.SidePanelHeadingContainer>
                <S.LogoText>Purrs N' Paws</S.LogoText>
            </S.SidePanelHeadingContainer>
            <List>
                { navMenu.map((item, index) => (
                    <ListItem key = { index } disablePadding>
                        <S.StyledNavMenu>
                            { item.icon }
                            <S.NavText primary = { item.text } />
                        </S.StyledNavMenu>
                    </ListItem>
                )) }
            </List>
        </S.SidePanel>
    );
};

export default LeftNavBar;

// TODO: 
// 1. Add Link to all menus