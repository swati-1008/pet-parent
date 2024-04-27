import React from "react";
import reelsList from "./reelsList";
import * as S from './styles';

const Reels = () => {
    return <>
        { reelsList.map((reel) => {
            return <S.ReelDiv src={ reel.reelImage } seen={ reel.seen ? '#f906628c' : 'white' } />
        }) }
    </>
};

export default Reels;

// TODO: 
// 1. Check react libraries like - https://www.npmjs.com/package/react-native-insta-story 