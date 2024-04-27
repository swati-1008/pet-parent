import React, { useState } from "react";
import { Drawer, List } from '@mui/material';
import * as S from './styles';
import suggestedPeopleList from './suggestedPeopleList';
import { Avatar } from '@mui/material';

const RightPanel = () => {
    /* eslint-disable no-unused-vars */
    const [collapsed, setCollapsed] = useState(false);
    
    return <>
        <Drawer 
            variant="permanent" 
            open={collapsed} 
            PaperProps={{ 
                sx: { 
                    borderWidth: '0px', 
                    left: 'auto', 
                } 
            }}
        >
            <S.StyledLogo>
                <S.StyledFontLogo>
                    People you may know
                </S.StyledFontLogo>
            </S.StyledLogo>
            <List>
                { suggestedPeopleList.map((user) => {
                    return <S.SuggestedPeople>
                            <Avatar src={user.avatar} style={{ display: 'inline-block' }} />
                            <S.UserHandle>{ user.userHandle }</S.UserHandle>
                        </S.SuggestedPeople>
                }) }
            </List>
        </Drawer>
        </>
};

export default RightPanel;