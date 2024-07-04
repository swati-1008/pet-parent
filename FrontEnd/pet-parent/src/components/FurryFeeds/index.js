import React from "react";
import * as S from './styles';
import LeftNavBar from "./LeftNavBar";
import RightPanel from "./RightPanel";
import Feed from "./Feed";
import CreatePost from "./CreatePost";
import Reels from "./Reels";

const FurryFeeds = () => {
    return (
        <S.BodyContainer>
            <div style = {{ flex: '0.155', flexDirection: 'column' }}>
                <LeftNavBar />
            </div>
            <div style = {{ flex: '0.5', flexDirection: 'column' }}>
                <Reels />
                <CreatePost />
                <Feed />
            </div>
            <div style = {{ flex: '0.345', flexDirection:'column' }}>
                <RightPanel />
            </div>
        </S.BodyContainer>
    )
};

export default FurryFeeds;