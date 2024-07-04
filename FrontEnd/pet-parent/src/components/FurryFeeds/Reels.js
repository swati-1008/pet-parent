import React from "react";
import * as S from './styles';
import reels from "./reelsList";

const Reels = () => {
    return (
        <S.ReelsContainer>
            <S.ReelsBlock>
                { reels.map((reel) => {
                    return (
                        <S.Reel>
                            <S.ReelImageDiv>
                                <S.ReelImage src = { reel.profilePicture } alt = 'DP' />
                            </S.ReelImageDiv>
                            <S.ReelUsername>{ reel.username }</S.ReelUsername>
                        </S.Reel>
                    )
                }) }
            </S.ReelsBlock>
        </S.ReelsContainer>
    )
};

export default Reels;