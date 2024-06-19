import React, { useEffect } from "react";
import * as S from './styles';
import { List } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { fetchSuggestedPeople } from "../../redux/actions/peopleYouMayKnowAction";

const RightPanel = () => {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.auth.user);
    console.log('user ', user)
    const suggestedPeople = useSelector((state) => state.peopleYouMayKnow.suggestedPeople);

    useEffect(() => {
        if (user)
            dispatch(fetchSuggestedPeople(user.userId));
    }, [dispatch, user]);
    return (
        <S.SidePanel
            variant = 'permanent'
            anchor = 'right'
            width = '340px'
        >
            <List>
                <S.SidePanelHeadingContainer>
                    <S.HeadingText>People you may Know</S.HeadingText>
                </S.SidePanelHeadingContainer>
                { suggestedPeople.map((person) => (
                    <S.RightPanelListItem key = { person.userId }>
                        <S.StyledAvatar src = { person.profilePicture } />
                        <S.StyledListItemText primary = { person.username } />
                    </S.RightPanelListItem>
                )) }
            </List>
        </S.SidePanel>
    )
};

export default RightPanel;