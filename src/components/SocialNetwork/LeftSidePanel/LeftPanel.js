import React, { useState } from "react";
import { Drawer, List, ListItem, ListItemButton, ListItemIcon, ListItemText } from '@mui/material';
import { Home, Search, Explore, SlowMotionVideo, Notifications, ChatBubbleOutline, AccountCircle, Bookmark } from '@mui/icons-material';
import './styles.css';

const LeftSidePanel = () => {
    const [collapsed, setCollapsed] = useState(false);
    const leftMenu = ['Home', 'Search', 'Explore', 'Reels', 'Notifications', 'Messages', 'Profile', 'Saved'];

    const ItemIcon = (text, className) => {
        switch(text) {
            case "Home": return <Home className={ className } />;
            case "Search": return <Search className={ className } />;
            case "Explore": return <Explore className={ className } />;
            case "Reels": return <SlowMotionVideo className={ className } />;
            case "Notifications": return <Notifications className={ className } />;
            case "Messages": return <ChatBubbleOutline className={ className } />;
            case "Profile": return <AccountCircle className={ className } />;
            case "Saved": return <Bookmark className={ className } />;
            default: return;
        }
    }

    return <>
        <Drawer variant="permanent" open={collapsed} PaperProps={{ sx: { width: "240px" }, }}>
            <div className="logo">
                <span className="font-logo">
                    Pet Fever
                </span>
            </div>
            <List>
                {leftMenu.map((text) => (
                    <ListItem key={text} disablePadding>
                        <ListItemButton>
                            <ListItemIcon className="list">
                                { ItemIcon(text, 'list-icon') }
                            </ListItemIcon>
                            <ListItemText primary={text} />
                        </ListItemButton>
                    </ListItem>
                ))}
            </List>
        </Drawer>
        </>
};

export default LeftSidePanel;

// TODO: 
// 1. Make drawer collapsible - use collapsed state
// 2. Add links to all list items