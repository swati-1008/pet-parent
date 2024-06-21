import React from "react";
import * as S from './styles';
import LeftNavBar from "./LeftNavBar";
import RightPanel from "./RightPanel";
import CreatePost from "./CreatePost";

const FurryFeeds = () => {
    return (
        <S.BodyContainer>
            <LeftNavBar />
            <CreatePost />
            <RightPanel />
        </S.BodyContainer>
    )
};

export default FurryFeeds;

// TODO: 
// 1. Add Reels component