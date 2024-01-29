import React from "react";
import { useState } from 'react';
import moment from "moment";
import { Avatar, Badge } from '@mui/material';
import { Favorite, FavoriteBorder, ChatBubbleOutline, Share, BookmarkBorder, Bookmark } from '@mui/icons-material';
import avatar from '../../../assets/images/dp1.jpeg';
import content from '../../../assets/images/content1.jpeg';
import * as S from './styles';

const Post = () => {
    const post = {
        userImage: avatar, 
        userHandle: 'test123', 
        body: content, 
        createdAt: new Date().toISOString(), 
        liked: true, 
        likeCount: 8712, 
        commentCount: 125, 
        saved: true, 
        savedCount: 500, 
    }
    const [liked, setLiked] = useState(post.liked);
    const [saved, setSaved] = useState(post.saved);

    let countFormatter = Intl.NumberFormat('en', { notation: 'compact' });
    return (
        <>
            <S.StyledCard variant="outlined">
                <S.TitleBar>
                    <Avatar src={post.userImage} style={{ display: "inline-block" }} />
                    <S.UserHandle>{ post.userHandle }</S.UserHandle>
                    <S.PostedTime>
                        - Posted &nbsp;
                        <span>
                            {moment(post.createdAt).startOf("seconds").fromNow()}
                        </span>
                    </S.PostedTime>
                </S.TitleBar>
                <S.Content>
                    <S.ContentImage src={ post.body } />
                </S.Content>
                <S.ActionsBar>
                    <S.Actions>
                        {liked === true ? (
                            <Favorite
                                style={{ fontSize: "20px" }}
                                onClick={() => setLiked(false)}
                            />
                            ) : (
                            <FavoriteBorder
                                style={{ fontSize: "20px" }}
                                onClick={() => setLiked(true)}
                            />
                        )}
                        <S.Count>{ countFormatter.format(post.likeCount) }</S.Count>
                    </S.Actions>
                    <S.Actions>
                        <ChatBubbleOutline style={{ fontSize: "20px" }} />
                        <S.Count>{ countFormatter.format(post.commentCount) }</S.Count>
                    </S.Actions>
                    <S.Actions>
                        {saved === true ? (
                            <Bookmark style={{ fontSize: '20px' }} onClick={() => setSaved(false)} />
                            ) : (
                            <BookmarkBorder style={{ fontSize: '20px' }} onClick={() => setSaved(true)} />
                        )}
                        <S.Count>{ countFormatter.format(post.savedCount) }</S.Count>
                    </S.Actions>
                    <Share style={{ fontSize: "20px" }} />
                </S.ActionsBar>
            </S.StyledCard>
        </>
    )
}

export default Post;