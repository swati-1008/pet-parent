import React, { useEffect, useState } from "react";
import * as S from './styles';
import { Modal } from "@mui/material";
import ReactInstaStories from "react-insta-stories";
import { useDispatch, useSelector } from "react-redux";
import { fetchReels } from "../../redux/actions/reelAction";

const Reels = () => {
    const dispatch = useDispatch();

    const reels = useSelector((state) => state.reels.reels);

    const [selectedReel, setSelectedReel] = useState(null);

    const handleReelClick = (reel) => {
        setSelectedReel(reel);
    }

    const handleCloseModal = () => {
        setSelectedReel(null);
    }

    useEffect(() => {
        dispatch(fetchReels(dispatch))
    }, [dispatch]);

    const stories = selectedReel?.media.map((media) => ({
        content: ({ action }) => (
            <S.StoryContent>
                <S.StoryImage 
                    src = { media } 
                    alt = 'Story Content' 
                    onClick = { () => action('next') } 
                />
            </S.StoryContent>
        ),
    }));

    const storyContentStyles = {
        width: '100%', 
        height: '100%', 
        display: 'flex', 
        justifyContent: 'center', 
        alignItems: 'center', 
    };

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
                            storyContentStyles = { storyContentStyles }
                        />
                    </S.ReelModalBox>
                </Modal>
            ) }
        </S.ReelsContainer>
    )
};

export default Reels;