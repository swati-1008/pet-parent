import React, { useState } from "react";
import { Drawer, List, ListItem, ListItemButton, ListItemIcon, ListItemText } from '@mui/material';
import { Home, Search, Explore, SlowMotionVideo, Notifications, ChatBubbleOutline, AccountCircle, Bookmark } from '@mui/icons-material';
import * as S from './styles';

const RightPanel = () => {
    const [collapsed, setCollapsed] = useState(false);
    const leftMenu = ['Home', 'Search', 'Explore', 'Reels', 'Notifications', 'Messages', 'Profile', 'Saved'];

    const ItemIcon = (text) => {
        switch(text) {
            case "Home": return <Home style={{ color: 'black' }} />;
            case "Search": return <Search style={{ color: 'black' }} />;
            case "Explore": return <Explore style={{ color: 'black' }} />;
            case "Reels": return <SlowMotionVideo style={{ color: 'black' }} />;
            case "Notifications": return <Notifications style={{ color: 'black' }} />;
            case "Messages": return <ChatBubbleOutline style={{ color: 'black' }} />;
            case "Profile": return <AccountCircle style={{ color: 'black' }} />;
            case "Saved": return <Bookmark style={{ color: 'black' }} />;
            default: return;
        }
    }
    
    return <>
        <Drawer 
            variant="permanent" 
            open={collapsed} 
            PaperProps={{ 
                sx: { 
                    width: "240px", 
                    borderWidth: '0px', 
                    left: 'auto', 
                } 
            }}
        >
            <S.StyledLogo>
                <S.StyledFontLogo>
                    Pet Fever
                </S.StyledFontLogo>
            </S.StyledLogo>
            <List>
                {leftMenu.map((text) => (
                    <ListItem key={text} disablePadding>
                        <ListItemButton>
                            <S.StyledListIcon>
                                { ItemIcon(text) }
                            </S.StyledListIcon>
                            <ListItemText primary={text} />
                        </ListItemButton>
                    </ListItem>
                ))}
            </List>
        </Drawer>
        </>
};

export default RightPanel;