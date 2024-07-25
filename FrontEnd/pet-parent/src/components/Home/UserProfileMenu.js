import React, { useState } from "react";
import { IconButton, Menu, MenuItem, Typography } from '@mui/material';
import accountIcon from '../../assets/images/dp1.jpeg';
import * as S from './styles';
import { useDispatch } from "react-redux";
import { logout } from "../../redux/actions/authAction";

const UserProfileMenu = () => {
    const dispatch = useDispatch();
    const [anchorEl, setAnchorEl] = useState(null);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    }

    const handleClose = () => {
        setAnchorEl(null);
    }

    const handleLogout = () => {
        dispatch(logout());
    }

    return (
        <div>
            <IconButton
                edge = 'end'
                aria-controls = 'user-menu'
                aria-haspopup = { true }
                onClick = { handleClick }
                color = 'inherit'
                style = {{ marginLeft: '50px' }}
            >
                <S.ProfileIcon src = { accountIcon } alt = 'User Profile' />
            </IconButton>
            <Menu
                id = 'user-menu'
                anchorEl = { anchorEl }
                keepMounted
                open = { Boolean(anchorEl) }
                onClose = { handleClose }
            >
                <MenuItem onClick = { handleClose }>
                    <Typography variant = 'inherit'>Profile</Typography>
                </MenuItem>
                <MenuItem onClick = { handleClose }>
                    <Typography variant = 'inherit'>Notifications</Typography>
                </MenuItem>
                <MenuItem onClick = { handleClose }>
                    <Typography variant = 'inherit' onClick = { handleLogout }>Logout</Typography>
                </MenuItem>
            </Menu>
        </div>
    );
}

export default UserProfileMenu;