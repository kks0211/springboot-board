package com.springboot.board.service;

import com.springboot.board.common.FileUtils;
import com.springboot.board.entity.BoardEntity;
import com.springboot.board.entity.BoardFileEntity;
import com.springboot.board.repository.JpaBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaBoardServiceImpl implements JpaBoardService {

    private final JpaBoardRepository jpaBoardRepository;

    private final FileUtils fileUtils;

    @Override
    public List<BoardEntity> selectBoardList() throws Exception {
        return jpaBoardRepository.findAllByOrderByBoardIdxDesc();
    }

    @Override
    public void saveBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        board.setCreatorId("admin");
        List<BoardFileEntity> list = fileUtils.parseFileInfo(multipartHttpServletRequest);
        if (CollectionUtils.isEmpty(list) == false) {
            board.setFileList(list);
        }
        jpaBoardRepository.save(board);
    }

    @Override
    public BoardEntity selectBoardDetail(int boardIdx) throws Exception {
        Optional<BoardEntity> optional = jpaBoardRepository.findById(boardIdx);
        if (optional.isPresent()) {
            BoardEntity board = optional.get();
            board.setHitCnt(board.getHitCnt() + 1);
            jpaBoardRepository.save(board);

            return board;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public void deleteBoard(int boardIdx) {
        jpaBoardRepository.deleteById(boardIdx);
    }

    @Override
    public BoardFileEntity selectBoardFileInformation(int boardIdx, int idx) throws Exception {
        BoardFileEntity boardFile = jpaBoardRepository.findBoardFile(boardIdx, idx);
        return boardFile;
    }
}
