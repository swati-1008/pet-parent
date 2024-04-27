import * as S from './styles';
import CreatePost from "./CreatePost/CreatePost";
import LeftSidePanel from "./LeftSidePanel/LeftPanel";
import RightPanel from "./RightSidePanel/RightPanel";
import Posts from './Posts/Posts';
import Reels from './Reels/Reels';

export function SocialNetwork () {
    return (
        <div style={{ display: 'flex' }}>
            <S.LeftPanel>
                <LeftSidePanel />
            </S.LeftPanel>
            <S.Body>
                <Reels />
                <CreatePost />
                <Posts />
            </S.Body>
            <S.RightPanel>
                <RightPanel />
            </S.RightPanel>
        </div>
    )
}