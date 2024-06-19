import React from "react";
import * as S from './styles';
import LeftNavBar from "./LeftNavBar";
import RightPanel from "./RightPanel";

const FurryFeeds = () => {
    return (
        <S.BodyContainer>
            <LeftNavBar />
            <RightPanel />
        </S.BodyContainer>
    )
};

export default FurryFeeds;