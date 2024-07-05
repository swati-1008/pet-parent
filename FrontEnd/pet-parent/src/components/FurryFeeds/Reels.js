import React, { useState } from "react";
import * as S from './styles';
import reels from "./reelsList";
import { Modal } from "@mui/material";
import ReactInstaStories from "react-insta-stories";

const Reels = () => {
    const [selectedReel, setSelectedReel] = useState(null);

    const handleReelClick = (reel) => {
        setSelectedReel(reel);
    }

    const handleCloseModal = () => {
        setSelectedReel(null);
    }

    const stories = selectedReel?.media.map((media) => ({
        content: ({ action }) => (
            <S.StoryContent media = { media } onClick = { action('next') } />
        ), 
    }));

    return (
        <S.ReelsContainer>
            <S.ReelsBlock>
                { reels.map((reel) => {
                    return (
                        <S.Reel key = { reel.id } onClick = { () => handleReelClick(reel) }>
                            <S.ReelImageDiv>
                                <S.ReelImage src = { reel.profilePicture } alt = 'DP' />
                            </S.ReelImageDiv>
                            <S.ReelUsername>{ reel.username }</S.ReelUsername>
                        </S.Reel>
                    )
                }) }
            </S.ReelsBlock>
            { selectedReel && (
                <Modal open = { true } onClose = { handleCloseModal }>
                    <S.ReelModalBox>
                        <ReactInstaStories 
                            stories = { stories }
                            defaultInterval = { 1500 }
                            width = { 900 }
                            height = { 700 }
                            onAllStoriesEnd = { handleCloseModal }
                        />
                    </S.ReelModalBox>
                </Modal>
            ) }
        </S.ReelsContainer>
    )
};

export default Reels;