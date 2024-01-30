import React, { useState } from 'react';
import { CardContent } from '@mui/material';
import avatar from '../../../assets/images/dp1.jpeg'
import * as S from './styles';

const CreatePost = () => {
    const [body, setBody] = useState("");
    const handleSubmit = () => {
        console.log('Submit successful');
    }
    return (
        <S.StyledCard variant='outlined'>
          <CardContent style={{ height: '60px', display: 'flex', marginTop: '10px' }}>
            <S.StyledAvatar src={avatar} />
            <S.StyledTextField
                variant='standard'
                required 
                placeholder="What's on your mind..." 
                value={body}
                multiline
                maxRows={4}
                onChange={(e) => setBody(e.target.value)}
                InputProps={{ disableUnderline: true }}
            />
            <S.StyledSubmit 
                variant='contained' 
                onClick={handleSubmit} 
            >
                Post
            </S.StyledSubmit>
          </CardContent>
        </S.StyledCard>
      );
}

export default CreatePost;