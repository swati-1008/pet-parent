import React from "react";
import { useState } from 'react';
import moment from "moment";
import { Avatar } from '@mui/material';
import { Favorite, FavoriteBorder, ChatBubbleOutline, Share, BookmarkBorder, Bookmark } from '@mui/icons-material';
import * as S from './styles';

const Post = (props) => {
    const [liked, setLiked] = useState(props.post.liked);
    const [saved, setSaved] = useState(props.post.saved);

    let countFormatter = Intl.NumberFormat('en', { notation: 'compact' });
    return (
        <>
            <S.StyledCard variant="outlined">
                <S.TitleBar>
                    <Avatar src={props.post.userImage} style={{ display: "inline-block" }} />
                    <S.UserHandle>{ props.post.userHandle }</S.UserHandle>
                    <S.PostedTime>
                        - Posted &nbsp;
                        <span>
                            {moment(props.post.createdAt).startOf("seconds").fromNow()}
                        </span>
                    </S.PostedTime>
                </S.TitleBar>
                <S.Content>
                    <S.ContentImage src={ props.post.body } />
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
                        <S.Count>{ countFormatter.format(props.post.likeCount) }</S.Count>
                    </S.Actions>
                    <S.Actions>
                        <ChatBubbleOutline style={{ fontSize: "20px" }} />
                        <S.Count>{ countFormatter.format(props.post.commentCount) }</S.Count>
                    </S.Actions>
                    <S.Actions>
                        {saved === true ? (
                            <Bookmark style={{ fontSize: '20px' }} onClick={() => setSaved(false)} />
                            ) : (
                            <BookmarkBorder style={{ fontSize: '20px' }} onClick={() => setSaved(true)} />
                        )}
                        <S.Count>{ countFormatter.format(props.post.savedCount) }</S.Count>
                    </S.Actions>
                    <Share style={{ fontSize: "20px" }} />
                </S.ActionsBar>
            </S.StyledCard>
        </>
    )
}

export default Post;